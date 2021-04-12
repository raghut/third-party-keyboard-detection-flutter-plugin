import 'dart:async';

import 'package:flutter/services.dart';

class KeyboardDetectionPlugin {
  static const MethodChannel _channel =
      const MethodChannel('keyboard_detection_plugin');

  ///Get the status of third party keyboard being used by the user.
  ///
  ///tru: If the user is using third party keyboard
  ///false: If the user is using system default keyboard
  static Future<bool> get isThirdPartyKeyboardUsing async {
    final bool status = await _channel.invokeMethod('isThirdPartyKeyboard');
    return status;
  }
}
