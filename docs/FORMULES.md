# ceci est la page des formules ....
## Combien je devrai chaque mois:
### Remboursement mensuel sur la feuille Ecxcel de Nicolas
* _**=-VPM( $C4/(12 x 100) ; $D4 x 12 ; $B4 ; ; 0)**_
* Avec:
  * *B4* : Le montant du prêt
  * *C4* : Le taux fixe
  * *D4* : La durée annuelle _Le nombre d'années de Remboursement
#### Mais qu'est ce que le VPM d'Excel
* J'ai trouvé le forumle sur [Un Forum Finance](http://forum.actufinance.fr/formule-mathematique-de-vpm-vc-et-va-P45065/)
* La réponse proposée :
> Pour VPM seulement, car je l'ai immédiatement sous la main :

> a = Vd*t/(1-(1+t)^(-n))

> a = annuités, mensualité,

> Vd = Capital emprunté,

>t= taux annuel, mensuel,

> n= nombre d'année, de mois.

> Attention : soit tout se base sur les mois soit sur les années pour que la
formule marche (avec t > 0)

> Pour le reste, il suffit de refaire les équations ...
>  Bonne chance

* ce qui donne en Python:
``` python
Vd=100000
n=15
t=2/100
a = Vd*t/(1-pow(1+t,-n))
m=a/12
print(m) #ce qui donne 648.5456020853672
```
* Cequi donne en java:
```java
// calculate  what I will have to pay monthly ...
 double monthlyamount = Math.pow(loanAmount * rate / (1 - (1 + rate)), -years * 12.0);
///show it to me !!!
monthlyTextView.setText(currencyFormat.format(monthlyamount));
```
* le résultat de _648,5 €_ est à comparer avec ce que produit la feuille Excel de Nicolas !!!
  * à savoir __643,5 €__
## Combien me coûtera mensuellementl'assurance  ?
### la formule de Nicolas:
* ___=F4 x  $B4/(12 x 100)___
  * *B4* : Le montant du prêt
  * *F4* : Le Taux d'aassurance

## En déduire le coût des intérêts
* ___=$E4 x $D4 x 12 - $B4___
  * *B4* : Le montant du prêt
  * *D4* : La durée annuelle _Le nombre d'années de Remboursement_
  * *E4* : La mensualité du prêt calculée plus haut !!!!

### à porter sous Android

# still TODO
* présenter les différentes dépenses de façon structurée !!!
