cmake_minimum_required(VERSION 3.20)
project(bank_aigrithym)

set(CMAKE_CXX_STANDARD 14)

add_executable(bank_aigrithym main.cpp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -fexec-charset=GBK")