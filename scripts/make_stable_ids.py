#!/usr/bin/env python3
import re, sys
from pathlib import Path

src = Path(sys.argv[1])
out = Path(sys.argv[2])
mapping = {}
for path in src.rglob("R.java"):
    text = path.read_text(errors="replace")
    current = None
    for line in text.splitlines():
        m = re.search(r"public static final class (\w+)", line)
        if m:
            current = m.group(1)
        m = re.search(r"public static final int (\w+)\s*=\s*(0x[0-9a-fA-F]{8})", line)
        if m and current:
            value = int(m.group(2), 16)
            if value >> 24 != 0x7f:
                continue
            name = f"{current}/{m.group(1)}"
            old = mapping.get(name)
            if old is not None and old != value:
                raise SystemExit(f"Conflicting resource ID for {name}: {old:#x} vs {value:#x}")
            mapping[name] = value

with out.open("w") as f:
    for name, value in sorted(mapping.items(), key=lambda kv: kv[1]):
        f.write(f"{value:#010x} {name}\n")
print(f"Generated {len(mapping)} stable resource IDs")
