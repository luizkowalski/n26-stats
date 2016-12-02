#!/bin/bash
REPEAT=0
while [  $REPEAT -lt 1 ]; do
   curl -X POST --header 'Content-Type: application/json' http://127.0.0.1:8080/transactions -d "{\"amount\":15, \"timestamp\": $(date +%s999)}"
   let REPEAT=REPEAT+1
done
