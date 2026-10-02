#!/usr/bin/env python3
import sys, zipfile
from pathlib import Path

apk_in = Path(sys.argv[1])
libdir = Path(sys.argv[2])
apk_out = Path(sys.argv[3])

with zipfile.ZipFile(apk_in, "r") as zin, zipfile.ZipFile(apk_out, "w", zipfile.ZIP_DEFLATED) as zout:
    existing = set()
    for item in zin.infolist():
        # aapt2 won't have the native libs; preserve everything else.
        if item.filename.startswith("lib/arm64-v8a/"):
            continue
        zout.writestr(item, zin.read(item.filename))
        existing.add(item.filename)
    for so in sorted(libdir.glob("*.so")):
        zout.write(so, f"lib/arm64-v8a/{so.name}")
