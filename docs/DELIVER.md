# Envoyer son application sur le téléphone
## en passant en mode Debug:
* cf. paragraphe 1.9.4 de mon livre __[Android 6 For Programmers, an app driven approach](http://www.informit.com/store/android-6-for-programmers-an-app-driven-approach-9780134289366)__
  * J'ai dû relancer le bon ordre pour passer le smartphone en mode DEBU
    * en suivant [cette réponse Stacoverflow - car j'avais kle même message d'erreur](http://stackoverflow.com/questions/26079838/android-studio-adb-error-device-unauthorized-please-check-the-confirmat)
### ce que fait adb:
* la console me répond:

```bash
#07/15 11:17:40: Launching app
$ adb push /home/jpmena/AndroidStudioProjects/LoanCalculator/app/build/outputs/apk/app-debug.apk /data/local/tmp/eu.jpmena.loancalculator
$ adb shell pm install -r "/data/local/tmp/eu.jpmena.loancalculator"
	pkg: /data/local/tmp/eu.jpmena.loancalculator
Success


$ adb shell am start -D -n "eu.jpmena.loancalculator/eu.jpmena.loancalculator.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
Connecting to eu.jpmena.loancalculator
Connected to the target VM, address: 'localhost:8600', transport: 'socket'
```
* La première partie dessus installe le apk vers la deveice connected (fonctionne aussi dans le cass d'une emulatedd device).
* __Je note que le mode DEBUG sur le téléphone est très rapide__
  * _Notamment il recharge l'application sans q'on ait besoin de la ddéinsstaller auparavant !!!!_
  *
