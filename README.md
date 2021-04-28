# About

This plugin helps to detect whether the user is using System Keyboard or Third party keyboard in Android device.
And it will provide functionality to open system keyboard manager to change keyboard.


## Why use keyboard_detection_plugin
- It would not be an exaggeration to say that people are addicted to downloading apps for every next reason. While doing this, one might just skip looking for safe keyboard apps for Android or iOS and might end up compromising on the security.

- Today, you use the keyboard to enter the login credentials for all your social networking and mobile banking websites. So, an unsafe keyboard application serves the purpose of someone constantly looking over your shoulder into your screen. 
- This constant monitor of the on-screen items will inadvertently lead to a data leak wherein the app owners get an instant access to each and everything that you type on your phone.
- If your application has requirement to avoid this security risk then by using this plugin you can alert the user or block the functionality of your app.

## Install
Add this to your package's pubspec.yaml file:
```yaml
dependencies:
  keyboard_detection_plugin: <latest version>
```
Get the latest version from ![here](https://github.com/raghut/third-party-keyboard-detection-flutter-plugin/releases/tag/v0.0.1)

## How this plugin will help?
This plugin is for only Android. We can enforce to use system default keyboard in iOS.
### Android: 
- We can't enforce Android System to use system keyboard dynamically by doing code level changes in Android app.
- We can only detect whether the user is using third party keyboard or not. Developer can show warning message or blocked screen to the user.
- It will provide functionality to open Android system keyboard manager.
#### Usage

##### 1. To get the thirdparty keyboard status
```dart
bool isThirdPartyKeyboardUsing =
          await KeyboardDetectionPlugin.isThirdPartyKeyboardUsing;
```
Output:
```
true or false
```

##### 2. To open Android system keyboard manager

```dart
KeyboardDetectionPlugin.openKeyboardChangeManager
```

### iOS
Add below code in **AppDelegate.Swift** to enforce system to use system default keyboard
```swift
func application(application: UIApplication, shouldAllowExtensionPointIdentifier extensionPointIdentifier: String) -> Bool {
    if (extensionPointIdentifier == UIApplication.ExtensionPointIdentifier.keyboard.rawValue) {
        return false
    }

    return true
}
```
