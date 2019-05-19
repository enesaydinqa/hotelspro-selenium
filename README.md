# hotelspro-selenium

- Parameters

-Dbrowser.type=chrome
-Dtake.a.video=false
-Dlanguage=tr/en (optional)


--> Serial Test Execute 

mvn clean install site -Dbrowser.type=chrome -Dtake.a.video=false -P "Serial"


--> Parallel Test Execute 

mvn clean install site -Dbrowser.type=chrome -Dtake.a.video=false -P "Parallel" -Dfork.count=2

