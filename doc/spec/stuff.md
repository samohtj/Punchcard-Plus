# This is the spec
Here's the file where I'm going to define all the different parts of the program. I will/should probably move this to the wiki for the GitHub repo, but I'll take care of that at a later date.

I'm doing this mostly because I read an article by our beloved Joel Spolsky on the necessity of specifications on any non-trivial project. And while this project is completely trivial, I still feel that it would be good practice to have some sort of spec for the software that I'm writing. At least I'll have something nice to show people.

# What the program does
* Keep a catalogue of user accounts.
* Organize the users into groups.
* Have a list of payment periods, with start and end dates.
* In the payment period, list all the workdays done by every user.
* Enable users to clock in and out.
* Display all this information.

# How it does said things
## User accounts
The user accounts are pretty simple. There's a UserAccount object that stores all the information for a user. It's just a simple Java object, that can be serialized. **Perhaps a Java Bean?** You know how those work, they're pretty useful. Not much here. I've already got a special object for holding passwords. However, I don't think I'll continue to use it. A simple 4-digit pin is all that's needed here.

The user account will not store any of the workday information. That will all be handled in the Workday object, discussed below. The user account object will be used only as a reference or tag, showing who the workday belongs to.

## User groups
There should be two different levels of user: worker and employer. Workers should be able to punch in, punch out, and view their own work history. For accountability reasons, workers shouldn't be able to go back in and edit past work. **Or maybe that should be an option for the employer to set up?** The emplyer will have the same ability for themselves, but they will also be able to go back and edit past work history for any of the workers under their control.

The application will only store one userbase at a time, for now. They will still be in a user group, just in case we want to be able to change that later. The group will have two categories: worker and employer. **Should the user account object be able to tell which group it is in?** Users should be able to be moved between groups by employer users. Meaning, the employers will need to be able to edit who is in what group, adding and removing users and such.

## Payment periods
Payment periods will be a simple tree view, with only three levels. The first level will be the year, the second the period in that year. The third level will have all the workdays inside that period, and should be sortable by different values (user, date, and such). The list of workdays should just be simple database entries, like "Jimmy worked from 10 am on Thursday till 5 pm on Thursday", stuff like that. Nothing fancy. When a user punches in, it should create a new workday, with the start date set as right now. When a user punches out, they should close the open workday with the end date as right now. Very simple. The method for punching in and out will be discussed below.

## Display
The main display should be broken up into three tabs: Users, Payment Periods, and Clock in/out. There aren't really any more features that are required. They'll all be discussed here.

### Users
This tab should have a list view of users, broken into two categories, as discussed above. I've yet to decide whether they will be in a table view, or in two list views.

There should be a single button to launch the *Edit User Data* screen, which will have a few more options. When that screen is launched, it should ask for a login of a current employer-level user. From there, there should be options to add a user, remove a user, and move a user from one group to another. Editing workday information will be done on the other tab.

### Payment periods
This tab will display work history for all the users, in a three-tiered tree view as discussed above. The first level will show the year, the second will show the payment period, and the third will show the workdays logged by each user during that period.

There should be a single extra button, just like in the last tab, to edit the payment period information. This will ask for a login, and show an edit screen. The employer will then be able to edit an old workday, remove a workday, or create a new workday under any user.

### Clock in/out
The final screen should have four items: username box, PIN box, login button, and action button. When the user types in a username and PIN and hits the login button, if the credentials are valid, the action button will become active. If the user has an active workday running, it will show "Punch Out", otherwise it will show "Punch In". They will both close the open workday or start a new one, respectively. Clicking it in either state will log out the user and the process can be started over again.
