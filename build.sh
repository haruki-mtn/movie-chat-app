#!/bin/bash
set -e

SRC_DIR=src/main/java
OUT_DIR=src/main/webapp/WEB-INF/classes
LIB_DIR=lib
CLASSPATH="$LIB_DIR/*"

mkdir -p "$OUT_DIR"
find "$OUT_DIR" -type f -name "*.class" -delete

javac \
-d "$OUT_DIR" \
-classpath "$CLASSPATH" \
$(find "$SRC_DIR" -type f -name "*.java")
