import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:keyboard_detection_plugin/keyboard_detection_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String keyboardErrorStatusMsg = '';
  bool _isThirdPartyKeyboardUsing;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String keyboardErrorStatusMsg =' Failed to get the keyboard status';
    bool isThirdPartyKeyboardUsing;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      isThirdPartyKeyboardUsing = await KeyboardDetectionPlugin.isThirdPartyKeyboardUsing;
    } on PlatformException {
      keyboardErrorStatusMsg = 'Failed to get the keyboard status.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _isThirdPartyKeyboardUsing =  isThirdPartyKeyboardUsing;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('ThirdPartyKeyboardStatus: $_isThirdPartyKeyboardUsing\n$keyboardErrorStatusMsg'),
        ),
      ),
    );
  }
}
