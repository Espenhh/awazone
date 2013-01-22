#!/usr/bin/env bash
set -e

cd `dirname $0`

info() { echo -e "\033[1;37m$1\033[0m"; }

if [ ! $# -eq 1 ]; then
	info "Bruk: ./app.sh [start|stop]"
	exit 1
fi

if [ $1 = "start" ]; then
	java -jar current/awazone.jar 2>> logs/error.log >> logs/server.log &
	PID=$!
	echo $PID > pid
	info "Startet app med PID $PID :)"
fi

if [ $1 = "stop" ]; then
	if [ -f pid ]; then
		PID=`cat pid`
		kill $PID
		rm pid
		info "Stoppet app med PID $PID :)"
	else
		info "Fant ikke PID-fil. Ser ikke ut til at appen kjører."
	fi
fi

