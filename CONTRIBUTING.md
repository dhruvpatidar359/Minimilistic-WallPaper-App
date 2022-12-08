# How to Contribute

If you think that you can add a new feature or want to fix a bug, we invite you to contribute to FunwithPhysics and make this project better. To start contributing, follow the below instructions:

1. Create a folder at your desire location (usually at your desktop).

2. Open Git Bash Here

3. Create a Git repository.

   Run command `git init`

4. Fork this Repo

5. Clone your forked repository of project.

```git clone
git clone https://github.com/dhruvpatidar359/Releastic-WallPaper-App.git
```

6. Navigate to the project directory.

```
cd Releastic-WallPaper-App
```

7. Add a reference(remote) to the original repository.

```
git remote add upstream https://github.com/dhruvpatidar359/Releastic-WallPaper-App.git
```

8. Check the remotes for this repository.

```
git remote -v
```

9. Always take a pull from the upstream repository to your main branch to keep it updated as per the main project repository.

```
git pull upstream main
```

10. Create a new branch(prefer a branch name that relates to your assigned issue).

```
git checkout -b <YOUR_BRANCH_NAME>
```
# Setup firebase configuration

11. First update your Android studio to the latest version.

12. To setup firebase for development on your local system, comment out all the firebase dependencies in the app level build.gradle file and run gradle sync.

13. Create a project on [Firebase](https://console.firebase.google.com/) and add the package name of the project com.example.wallpaperapper

14. Register your app with firebase.

16. Download the google-services.json file in your firebase project and replace it with the google-services.json present in the cloned project.

17. Uncomment all the firebase dependencies and run gradle sync again.

18. Perform your desired changes to the code base.

19. Check your changes.

```
git status
```

```
git  diff
```

20. Stage your changes.

```
git add . <\files_that_you_made_changes>
```

21. Commit your changes.

```
git commit -m "relavant message"
```

22. Push the committed changes in your feature branch to your remote repository.

```
git push -u origin <your_branch_name>
```

23. To create a pull request, click on `compare and pull requests`.

24. Add an appropriate title and description to your PR explaining your changes.

25. Click on `Create pull request`.

Congratulations🎉, you have made a PR to the Releastic-WallPaper-App.
Wait for your submission to be accepted and your PR to be merged by a maintainer.
