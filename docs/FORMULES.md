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

* ce qui donne en Javascript:

#### J'ai retrouvé la formule dans le code source du site [GuideDuCredit.com](http://www.guideducredit.com/pret-immobilier/simulation-pret-immobilier/simulation-mensualites.php#)
* Plus particulièrement dans code Javascrpt de la page principale
  * à partir de la ligne 1830  

``` javascript
ta = taux_champ / 100;
tm = ta / 12;

mens = (montant_pret) * (tm) / (1 - Math.pow(1/(1+tm),12*duree_emprunt));
taux_assurance_mensuel = (((montant_pret * 0.36)/100) / 12);

taux_assurance_annuel = (taux_assurance_mensuel * 12 * duree_emprunt);

mens += taux_assurance_mensuel;
mens = Math.round(mens);

s_mens = inttostr(mens);
s_cout = inttostr((mens * 12 * duree_emprunt) - montant_pret);

$("#idmens").text(s_mens);
$("#idcout").text(s_cout);
$("#taux_mensuel").text(inttostr(taux_assurance_mensuel));
$("#taux_annuel").text(inttostr(taux_assurance_annuel));
```
* Cequi donne en java:
```java
private void calculateAndDisplay() {

        /* calculate  what I will have to pay monthly ...
         * ta = taux_champ / 100;
		 * tm = ta / 12;
		 * mens = (montant_pret) * (tm) / (1 - Math.pow(1/(1+tm),12*duree_emprunt));
         */
        double monthlyrate=rate/12.0; //that is the tm above
        double monthlyPayments = (loanAmount * monthlyrate) / (1-Math.pow(1/(1+monthlyrate), 12 * years));

        ///show it to me !!!
        monthlyTextView.setText(currencyFormat.format(monthlyPayments));
    }
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
