cmake_minimum_required(VERSION 3.22.1)
project(nvscriptbridge)

add_library(nvscriptbridge SHARED
    nvscriptbridge.cpp
)

set_target_properties(nvscriptbridge PROPERTIES
    CXX_STANDARD 17
    CXX_STANDARD_REQUIRED YES
)

find_library(log-lib log)
target_link_libraries(nvscriptbridge
    ${log-lib}
    dl
)
