# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.20

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "D:\JetBrains\CLion 2021.2.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "D:\JetBrains\CLion 2021.2.3\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug"

# Include any dependencies generated for this target.
include CMakeFiles/Memory_allocation.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/Memory_allocation.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Memory_allocation.dir/flags.make

CMakeFiles/Memory_allocation.dir/main.cpp.obj: CMakeFiles/Memory_allocation.dir/flags.make
CMakeFiles/Memory_allocation.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir="D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Memory_allocation.dir/main.cpp.obj"
	D:\JetBrains\CLION2~1.3\X86_64~1.0-R\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Memory_allocation.dir\main.cpp.obj -c "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\main.cpp"

CMakeFiles/Memory_allocation.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Memory_allocation.dir/main.cpp.i"
	D:\JetBrains\CLION2~1.3\X86_64~1.0-R\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\main.cpp" > CMakeFiles\Memory_allocation.dir\main.cpp.i

CMakeFiles/Memory_allocation.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Memory_allocation.dir/main.cpp.s"
	D:\JetBrains\CLION2~1.3\X86_64~1.0-R\mingw64\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\main.cpp" -o CMakeFiles\Memory_allocation.dir\main.cpp.s

# Object files for target Memory_allocation
Memory_allocation_OBJECTS = \
"CMakeFiles/Memory_allocation.dir/main.cpp.obj"

# External object files for target Memory_allocation
Memory_allocation_EXTERNAL_OBJECTS =

Memory_allocation.exe: CMakeFiles/Memory_allocation.dir/main.cpp.obj
Memory_allocation.exe: CMakeFiles/Memory_allocation.dir/build.make
Memory_allocation.exe: CMakeFiles/Memory_allocation.dir/linklibs.rsp
Memory_allocation.exe: CMakeFiles/Memory_allocation.dir/objects1.rsp
Memory_allocation.exe: CMakeFiles/Memory_allocation.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir="D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug\CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Memory_allocation.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Memory_allocation.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Memory_allocation.dir/build: Memory_allocation.exe
.PHONY : CMakeFiles/Memory_allocation.dir/build

CMakeFiles/Memory_allocation.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Memory_allocation.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Memory_allocation.dir/clean

CMakeFiles/Memory_allocation.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation" "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation" "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug" "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug" "D:\JetBrains\CLion 2021.2.3\clionproject\Memory_allocation\cmake-build-debug\CMakeFiles\Memory_allocation.dir\DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/Memory_allocation.dir/depend
