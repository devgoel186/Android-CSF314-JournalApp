# Journal App

A Journal App that will store Journal tasks that will store tasks with date and time stamps. Users will also have the ability to share and delete tasks.

### a. Basic Information

**Name of the Project** - Journal App

**Name of Student** - Dev Goel

**BITS ID** - 2019A7PS0236G

**Email** - f20190236@goa.bits-pilani.ac.in

### b. What does the app do? Any known bugs?

The Journal App will store Journal tasks that will store tasks with date and time stamps. Users will also have the ability to share and delete tasks. The following bug exists in the app as of now:

- Clicking the floating action **Add** entry button creates a new entry in the database. This entry is now stored in the database and clicking the Back button now doesn't discard the entry, and it is stored as an empty entry displayed in the EntryList recycler view. However, a user expects that an entry is created only when clicking the **Save** button.

### c. Description of completed tasks and steps followed to achieve them

**Task 1 - Implementing Nav Graph Actions**

- The `entryListFragment` is the start destination for the nav graph.
- The `entryDetailsFragment` and `infoFragment` will originate from this home destination.
- The add new entry button will be used to add new entries in the application.

**Task 2 - Modifying Database**

- Added **INSERT, QUERY and UPDATE** queries for insert, get and update functionalities in the database. These queries are called by relevant actions in the app such as clicking the save button or clicking the floating action button.
- The **DELETE** button will delete the Journal entries.

**Task 3 - Delete Button on Menu Bar**

- Added and Implemented the Delete Button on the Menu bar.
- Clicking this button prompts the user with a final warning before deleting the entry.

**Task 4 - Share Button on Menu Bar**

- The Share Button is implemented using an implicit intent that is created on clicking the button.
- This prompts the user with applications stored on the phone that can be used to share a relevant message.

**Task 5 - Implementing the 'Info' button**

- The 'Info' button launches a new fragment by passing an intent.
- This fragment shows information about the app.

**Task 6 - Accessibility**

- I ran the application using **TalkBack** service. The service is fairly easy to use and guides the user on each and every step. Elements on the screen were read aloud. I could easily understand where my finger was on the screen and which element was active. Overall, TalkBack is a great accessibility tool which can help blind people or people with vision impairments to use apps.

- I ran **Accessibility Scanner** on the application, which reported the following issue:

  1. **Text contrast**: Inappropriate foreground to background contrast ratio.

  - **Current** : 3.57:1
  - **Suggested**: 4.50:1
  - This issue was fixed by adding setting the `android:textColor` to **#004D40**

  2. **Insufficient image color contrast ratio**: Low contrast ratio for the add entry floating action button.

### d. Testing using written test cases and monkey stress-testing

- I followed a test-driven approach for this assignment, writing and running test cases as I worked on the edge cases for the code.
- I wrote instrumented / UI tests and ran all tests after each step I completed in the assignment, while adding new test cases.
- For monkey stress testing, I ran the monkey tool successfully for 10000 iterations using the command `adb shell monkey -p androidsamples.java.journalapp -v 10000`.

Running tests on my app after each step helped me get a more detailed idea as to how the app was functioning, and also helped me figure out some corner cases which I corrected to avoid app crashes.
I ran the monkey tool successfully for 2500, 5000, and 10000 iterations. The app did not crash on any run.

### e. Approximate number of hours it took to complete the assignment

**Writing Code, Testing and Solving Accessibility Issues** -> 14 hours

**Documentation** -> 1 hour

**Total Time = 15 hours**

### f. Difficulty of Assignment

On a scale of 1 to 10, I would rate the difficulty of the assignment as **9**.

I feel the assignment was a bit on the difficult side, and the integral part was the management of the database and navigation handling using the nav graph. The assignment gave me a good understanding of the important functionalities and methods commonly used in App Development.
