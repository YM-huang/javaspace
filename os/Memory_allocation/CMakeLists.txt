cmake_minimum_required(VERSION 3.20)
project(Memory_allocation)

set(CMAKE_CXX_STANDARD 14)

add_executable(Memory_allocation main.cpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -fexec-charset=GBK")
