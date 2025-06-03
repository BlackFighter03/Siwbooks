# Casi d'uso (formato md)

### **Consultazione contenuti (Utenti occasionali/registrati)**
1. **Visualizza elenco libri**  
   - **Attore**: Utente occasionale/registrato  
   - **Scopo**: Mostrare tutti i libri con titolo, anno e anteprima immagine  
   - **Azione principale**: Accesso alla lista → Visualizzazione paginata  

2. **Visualizza dettaglio libro**  
   - **Attore**: Utente occasionale/registrato  
   - **Scopo**: Mostrare titolo, anno, immagini, autori collegati e recensioni  
   - **Precondizioni**: Libro esistente nel sistema  

3. **Visualizza elenco autori**  
   - **Attore**: Utente occasionale/registrato  
   - **Scopo**: Listare autori con nome, cognome e nazionalità  
   - **Azione principale**: Accesso alla lista → Filtri opzionali  

4. **Visualizza profilo autore**  
   - **Attore**: Utente occasionale/registrato  
   - **Scopo**: Mostrare nome, cognome, date, nazionalità, fotografia, libri correlati  
   - **Precondizioni**: Autore esistente nel sistema  

### **Gestione recensioni (Utenti registrati)**
5. **Aggiungi recensione**  
   - **Attore**: Utente registrato  
   - **Scopo**: Inserire una recensione (titolo, voto 1-5, testo)  
   - **Precondizioni**:  
     - Utente autenticato  
     - Nessuna recensione precedente per lo stesso libro  
   - **Azione principale**:  
     - Selezione libro → Compilazione form → Verifica unicità → Salvataggio  
   - **Eccezioni**: Recensione già esistente → Blocco operazione  

6. **Elimina recensione**  
   - **Attore**: Utente registrato  
   - **Scopo**: Eliminare la propria recensione su un libro  
   - **Precondizioni**: Recensione esistente e appartenente all'utente  
   - **Azione principale**: Selezione libro e recensione → Conferma eliminazione  
   - **Eccezioni**: Tentativo di eliminare recensione di un altro utente → Errore  

### **Gestione contenuti (Amministratori)**
7. **Aggiungi libro**  
   - **Attore**: Amministratore  
   - **Scopo**: Inserire nuovo libro con titolo, anno, immagini, autori  
   - **Azione principale**:  
     - Compilazione form → Associazione autori esistenti/creazione nuovi → Upload immagini → Salvataggio  

8. **Modifica libro**  
   - **Attore**: Amministratore  
   - **Scopo**: Aggiornare titolo, anno, immagini o autori  
   - **Precondizioni**: Libro esistente  
   - **Eccezioni**: Autore non esistente → Creazione preliminare richiesta  

9. **Elimina libro**  
   - **Attore**: Amministratore  
   - **Scopo**: Rimuovere libro e recensioni collegate  
   - **Azione principale**: Conferma eliminazione → Cancellazione a cascata  

10. **Aggiungi autore**  
    - **Attore**: Amministratore  
    - **Scopo**: Registrare nuovo autore con dati anagrafici e fotografia  
    - **Azione principale**: Inserimento dati → Upload foto → Verifica univocità  

11. **Modifica autore**  
    - **Attore**: Amministratore  
    - **Scopo**: Aggiornare dati anagrafici o fotografia  
    - **Precondizioni**: Autore esistente  

12. **Elimina autore**  
    - **Attore**: Amministratore  
    - **Scopo**: Rimuovere autore e scollegarlo dai libri (senza eliminare i libri)  
    - **Eccezioni**: Autore collegato a libri → Modifica obbligatoria dei libri prima  

13. **Elimina recensione**  
    - **Attore**: Amministratore  
    - **Scopo**: Rimuovere una recensione dal sistema  
    - **Azione principale**: Selezione della recensione → Conferma eliminazione 
