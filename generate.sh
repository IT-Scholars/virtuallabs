#!/bin/bash

rm -rf src/edu/fiu/cis/acrl/virtuallabs/ws
wsdl2java.sh -o . -sd -ss --noBuildXML -g -p edu.fiu.cis.acrl.virtuallabs.ws -uri virtuallabs.wsdl
cp VirtualLabsSkeleton.java src/edu/fiu/cis/acrl/virtuallabs/ws/
