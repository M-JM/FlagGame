Project Flag Game : 

A Small project to demonstrated the basics of Android app development.

Tested only on Emulators :

Pixel 2 API 
Pixel 2 API without GPS

Description : 

The game presents you a random flag for which you get 4 possible answers.
Upon selection the correct answer your score is added by 100 points
Upon wrong answer it adds 0 points
two hints are avaible which deducts each 25 points upon being pressed.
The game runs for 5 turns and then navigates to the endscore entering your score if it makes the top 10.
A training screen is present that contains all flags and displays their Name , Capital ( hint1) and Region (hint2)

The following elements are present in the app : 

- Use of fragments for view
- Use of ViewModels for logic and operations on data
- ROOM integration (SQL Lite database) - DAO - Queries
- Coroutines
- Two way binding
- Basic layout styling
- RecyclerView
- Classes / Methods / Functions
- API call
- SafeArgs
- Factory
- Adapters
- Navigation through NavHost and a mix of navigation on Events and onclicklistners.
- no hardcoded text // all declared as String values

Elements not currently present in the application andcould improve the quality of app.

- Intent Sharing
- Menu
- API call pushed in Database (caching of data in case of loss of connection)
- Proper UI design and use of themes
- ...

Currently know crashes:

Since the app doesn't use data from local database and api , a call is being made to the API to select a random country.
If the users clicks on an answer before the country has been selected you will get a KotlinNullPointerException since the value is being 
checked against a promised non-null value (!!)
I think this could be fixed by pushing Parsed data from the API call once in the Database and calling the list of countries from the Database.
To check any changes / discreptencies between API and Database a function can be implemented upon the first time we are on the titlescreen
when launching the app and from then on out only use the local DB ( numbers of countries/names are fairly constant and don't require constant calls to API to check for changes)

Currently know problems to be fixed or improved:

- Some navigation not being done by observing events.
- Some variables are declared but never used
- Some Functions can be improved
- The SafeArgs passed between Game and End is being called and set in the Fragment instead of the ViewModel.
- Encapsulation of Mutables data is missing 
- Lack of comments to explain the code
- General clean up of unused libraries
- Naming convention and name declarations of variables could vastly be improved. ( random use of PascalCase and camelCase )
- Constraint layout is fixed on the parent (not good practice)

