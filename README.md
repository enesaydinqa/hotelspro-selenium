# anywork-selenium

- Parameters

-Dremote.test=true
-Dbrowser.type=chrome
git c-Dtake.a.video=false


--> Serial Test Execute 

mvn clean install site -Dbrowser.type=chrome -Dtake.a.video=false -P "Serial"


--> Parallel Test Execute 

mvn clean install site -Dbrowser.type=chrome -Dtake.a.video=true -P "Parallel" -Dfork.count=1

