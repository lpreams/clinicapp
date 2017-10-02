# clinicapp

How to get started:

# tldr

*Check out project from Version Control* in Android Studio

## Full Instructions

1. Install Android Studio

2. Check out project from Version Control > GitHub
  
3. Set Git Repository URL: to https://github.com/lpreams/clinicapp
    - (Optional) Change Parent Directory and/or Directory Name if desired
    - Clone
    - When prompted "You have checked out a Studio project" > Yes
4. Wait patiently while Gradle takes *forever* to sync and build

5. Build > Make Project

6. If you want to run the app on a physical device, enable adb debugging and connect it over USB.
 - If you wish to use an emulator (recommended):
   - Tools > Android > AVD Manager > Create New Virtual Device > Phone or Tablet > Pick your favorite
   - Pick any Release with API Level 16+, probably one of the x86_64 releases > Download
   - Next > Landscape (or Portrait, either should work, and can be changed while running) > Finish 
   - Gree play button (toward the right) to start your new emulator

6. Run > Run 'app'
    - Select your connected device or emulator from the list > OK

7. Wait patiently *again* while Gradle takes forever to sync, build, upload, install, and launch the app. 
