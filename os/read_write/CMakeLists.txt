cmake_minimum_required(VERSION 3.20)
project(read_write)

set(CMAKE_CXX_STANDARD 14)

add_executable(read_write main.cpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -fexec-charset=GBK")