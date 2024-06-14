#!/usr/bin/env bash
set -eo pipefail
ROOT_PATH="$(cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd)/.."
javaPath="/home/crafting-bench/.jdks/corretto-1.8.0_372/bin/java"
${javaPath} -jar "$ROOT_PATH/out/artifacts/iri_creator_jar/iri-creator.jar"
