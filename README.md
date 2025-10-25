Nama : Eko Susilo
NIM : H1D023077
Shift : D
Shift KRS : B

![Screen record demo](./responsi_pemmob.gif "Screenrecord Responsi")

Alur 
- Request ke endpoint https://api.football-data.org/v4/teams/341 (sesuai dengan tim di spreadsheet)
- Hasil response json selanjutnya diparsing (serialize) lewat fitur nya Retrofit ke Data Class nya kotlin
- Data diambil lewat viewmodel
- View tampilkan hasilnya