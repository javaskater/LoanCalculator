# LoanCalculator
## Les éléments de la vuue
### les vues texte:
``` java
  private TextView amountTextView; // shows formatted bill amount
  private TextView rateTextView; // shows rate Amount I should negociate
  private TextView yearsTextView; // shows the number of years I expect to pay ...
  private TextView monthlyTextView; // shows calculated value have to pay (to be compared with my actual rent)
```
### les barres de réglage
``` java
SeekBar rateSeekBar =
         (SeekBar) findViewById(R.id.rateSeekBar);
 rateSeekBar.setOnSeekBarChangeListener(rateBarListener);

 // set percentSeekBar's OnSeekBarChangeListener
 SeekBar yearsSeekBar =
         (SeekBar) findViewById(R.id.yearsSeekBar);
 rateSeekBar.setOnSeekBarChangeListener(yearsBarListener);
```
## La dynamique
1 J'entre une valeur de somme à emprunter ...
  * les curseurs sont par défaut réglés sur
    * 1,5% pour le taux
    * et 1 année pour la durée de remboursement
2 Il me déduit à partir de [La Formule Magigue]()
  * Je parle de la formula magique plus sous [page formule](docs/FORMULES.md)
