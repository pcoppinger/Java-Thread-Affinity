#!/usr/bin/make -f
#
# Makefile for C code
#

# C sources to compile

TARGET_DIR := ../../../target/classes
TARGET := $(TARGET_DIR)/libCEInternals.so

WORKING_DIR := $(TARGET_DIR)/../jni

JAVA_CLASSES = software.chronicle.enterprise.internals.impl.NativeAffinity net.openhft.ticker.impl.JNIClock

JNI_STUBS := $(subst .,_,$(JAVA_CLASSES))
JNI_HEADERS := $(patsubst %,$(WORKING_DIR)/%.h,$(JNI_STUBS))
JNI_SOURCES := $(patsubst %,%.cpp,$(JNI_STUBS))
JNI_JAVA_SOURCES := $(patsubst %,$(TARGET_DIR)/%.class,$(subst .,/,$(JAVA_CLASSES)))

JAVA_BUILD_DIR := $(TARGET_DIR)

JAVA_HOME ?= /usr/java/default
JAVA_LIB := $(JAVA_HOME)/jre/lib
JVM_SHARED_LIBS := -L$(JAVA_LIB)/amd64/server -L$(JAVA_LIB)/i386/server  -L$(JAVA_LIB)/amd64/jrockit/ -L$(JAVA_LIB)/i386/jrockit/

TARGETTRIPLET := $(shell $(CC) -dumpmachine)
ifeq ($(JNI_PLATFORM),)
ifeq ($(findstring mingw,$(TARGETTRIPLET)),mingw)
JNI_PLATFORM:= win32
else
ifeq ($(findstring linux,$(TARGETTRIPLET)),linux)
JNI_PLATFORM:= linux
JVM_PLATFORM_LIBS:= -ljvm -lrt
else
ifeq ($(findstring darwin,$(TARGETTRIPLET)),darwin)
JNI_PLATFORM:= darwin
# add more checks here
endif
endif
endif
endif

CXX=g++
INCLUDES := -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/$(JNI_PLATFORM) -I $(WORKING_DIR)

# classpath for javah
ifdef CLASSPATH
JAVAH_CLASSPATH = $(JAVA_BUILD_DIR):$(CLASSPATH)
else
JAVAH_CLASSPATH = $(JAVA_BUILD_DIR)
endif

.PHONY : clean

all: $(TARGET)

$(TARGET): $(JNI_HEADERS) $(JNI_SOURCES)
	$(CXX) -O3 -Wall -shared -fPIC $(JVM_SHARED_LIBS) $(JVM_PLATFORM_LIBS) $(INCLUDES) $(JNI_SOURCES) -o $(TARGET)

$(JNI_HEADERS): $(JNI_JAVA_SOURCES)
	mkdir -p $(TARGET_DIR)/jni
	javah -force -classpath $(JAVAH_CLASSPATH) -d $(WORKING_DIR) $(JAVA_CLASSES)

clean:
	rm $(TARGET)
