# Proiect-Elemente-Avansate-de-Programare-2022

Aplicația a fost dezvoltată pentru a permite gestionarea unei librării.

__Obiecte create__:
* Carte
* Autor
* Editura
* Sectiune
* Categorii
* Manual scolar
* Tipuri manuale
* Sectiune Manuale

__Acțiuni/interogări__:
* Salvarea și încărcarea librăriei
* Adăugarea unei cărți în librărie
* Afișarea articolelor din librărie
* Afișarea cărților:
  - dintr-o secțiune
  - scrise de un autor
  - de la o editură
* Afișarea autorilor
* Sortarea cărților după titlu/crescător sau descrescător după preț
 
 Toate aceste aceste acțiuni sunt expuse în clasa Service, iar clasa Main apelează metodele clasei service.
 Datele pentru aceste acțiuni pot fi introduse de către utilizator, de la tastatură sau pot fi preluate din fișiere text.
 S-a implementat și un serviciu de audit care va genera un fișier de tip CSV în care vor fi înregistrare data și ora la care a fost efectuată una din acțiunile/interogările de mai sus.

