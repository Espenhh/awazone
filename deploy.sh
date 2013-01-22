#!/usr/bin/env bash

function yellow {
	echo -e "\033[33m$1\033[0m"
}

function bold {
	echo -e "\033[1;37m$1\033[0m"
}

yellow ""
yellow "  deployer                                   "
yellow "                                             "
yellow "      #  ######            ##    #####      #"
yellow "      #      #            #  #   #    #     #"
yellow "      #     #    #####   #    #  #    #     #"
yellow "      #    #             ######  #####      #"
yellow " #    #   #              #    #  #          #"
yellow "  ####   ######          #    #  #          #"
yellow ""

function fail {
	echo "\[${bold_green}\]$1\[${reset}\]"
	exit 1
}

DEFAULT_JAR=`find . | grep dependencies.jar`
DEFAULT_VERSION=`date +%Y%m%d%H%M%S`-SNAPSHOT

read -e -p "> Hvor ligger jar-filen? " -i $DEFAULT_JAR JAR
read -e -p "> Hvilken versjon? " -i $DEFAULT_VERSION VERSION
read -e -p "> Til test eller prod? " -i "test" ENV

if [ ! -f $JAR ]; then
	fail "Fant ikke $JAR :("
fi

if [ $ENV != "test" -a $ENV != "prod" ]; then
	fail "Miljø må være enten 'test' eller 'prod'"
fi

BASE="~/web/api-app/$ENV"
ssh javabin@javazone.espenhh.com "mkdir -p $BASE/$VERSION"
scp $JAR javabin@javazone.espenhh.com:$BASE/$VERSION/awazone.jar
ssh javabin@javazone.espenhh.com "ln -s -f $VERSION -T $BASE/current"

yellow "le done"
