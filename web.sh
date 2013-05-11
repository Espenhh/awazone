#/bin/bash

if [ ! -f /awezone.properties ]
then
    echo "Filen /awezone.properties (på rot av disken din) finnes ikke. Legg den der før du kan starte :)"
	exit 1
fi

cd awazone-web
mvn exec:java