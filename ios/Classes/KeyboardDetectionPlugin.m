#import "KeyboardDetectionPlugin.h"
#if __has_include(<keyboard_detection_plugin/keyboard_detection_plugin-Swift.h>)
#import <keyboard_detection_plugin/keyboard_detection_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "keyboard_detection_plugin-Swift.h"
#endif

@implementation KeyboardDetectionPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftKeyboardDetectionPlugin registerWithRegistrar:registrar];
}
@end
