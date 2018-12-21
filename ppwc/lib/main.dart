import 'dart:async';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_html_view/flutter_html_view.dart';



void main() => runApp(_widgetForRoute(window.defaultRouteName));

Widget _widgetForRoute(String route) {
  switch (route) {
    case 'singBottomView':
      return SingBottomWidget();

    default:
      return Center(
        child: Text('Unknown route: $route', textDirection: TextDirection.ltr),
      );
  }
}

class SingBottomWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return MM();
  }
}

class MM extends State {
  static const counterPlugin = const EventChannel('com.jzhu.counter/plugin');
  StreamSubscription _streamSubscription = null;
  var _eventStr;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _lisEvent();
  }

  @override
  void dispose() {
    super.dispose();
    //取消监听
    if (_streamSubscription != null) {
      _streamSubscription.cancel();
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return Container(
      padding: const EdgeInsets.only(left: 15.0, right: 15.0),
      color: Colors.white,
      child: SingleChildScrollView(
        child: new Column(
          children: <Widget>[
            new Text("签到规则",
                style: TextStyle(fontSize: 17, color: Color(0xFF666666)),
                textDirection: TextDirection.ltr),
            new Padding(
              padding: const EdgeInsets.only(top: 10.0, bottom: 15.0),
              child: new HtmlView(data: _eventStr),
            ),
          ],
        ),
      ),
    );
  }

  Future<Null> _lisEvent() async {
    String eventStr;
    try {
      _streamSubscription =
          counterPlugin.receiveBroadcastStream().listen((data) {
        eventStr = '$data';
        setState(() {
          _eventStr = eventStr;
        });
      });
    } on PlatformException catch (e) {
      eventStr = "event get data err: '${e.message}'.";
      setState(() {
        _eventStr = eventStr;
      });
    }
  }
}
