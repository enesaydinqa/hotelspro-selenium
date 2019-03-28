# nyxcosmetics

- Parameters

-Dremote.test=true
-Dbrowser.type=chrome
-Djetty.port=8090
-Dtake.a.video=false
-Dshipping.fee=7.99
-Dpr.for.shipping.free=120
-Dgift.package.fee=5.00


--> Serial Test Execute 

mvn clean install site -Dremote.test=false -Dbrowser.type=chrome -Djetty.port=8090 -Dtake.a.video=false -Dshipping.fee=7.99 -Dpr.for.shipping.free=120 -Dgift.package.fee=5.00 -P "Serial"


--> Parallel Test Execute 

mvn clean install site -Dremote.test=false -Dbrowser.type=chrome -Djetty.port=8090 -Dtake.a.video=false -Dshipping.fee=7.99 -Dpr.for.shipping.free=120 -Dgift.package.fee=5.00 -P "Parallel" -Dfork.count=5
