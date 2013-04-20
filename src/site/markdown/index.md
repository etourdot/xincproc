# XIncProc Framework
The XIncProc Frameword provides an almost complete implementation of W3C [XML Inclusions (XInclude) Version 1.0 (Second Edition)][xinclude].
Althougt XInclude is supported into Jaxp implementation since Java 1.5, this support is very incomplete.
XIncProc brings a most powerfull support including [xpointer][xpointer], [element][element], [xpath][xpath] and [xmlns][xmlns] schemes.

[xinclude]: http://www.w3.org/TR/xinclude/
[xpointer]: http://www.w3.org/TR/xptr-framework/
[element]: http://www.w3.org/TR/xptr-element/
[xpath]: http://www.w3.org/2005/04/xpointer-schemes/xpath
[xmlns]: http://www.w3.org/TR/xptr-xmlns/

## How to use
### Maven integration

To add XIncProc to your project, just add the following dependency to your `pom.xml`:
```xml
    <dependency>
        <groupId>org.etourdot</groupId>
        <artifactId>xincproc</artifactId>
        <version>1.0.0</version>
    </dependency>
```

### API Usage
```java
    // Open a stream
    final FileInputStream source = new FileInputStream(urlTest.getPath());
    // Parse it
    final ByteArrayOutputStream output = new ByteArrayOutputStream();
    XIncProcEngine.parse(source, urlTest.toExternalForm(), output);
    // That's all !
    final String result = output.toString("UTF-8");
```
Just have a look to [Javadocs](apidocs/index.html) for more samples

## Specifications and conformance
XIncProc conformance is tested against the official [Xinclude Test Suite](http://www.w3.org/XML/Test/XInclude/)

See [conformance here](xinclude/specs/org/etourdot/xincproc/xinclude/testsuite/TestSuite.html)

## Issue Tracking
You can create issues on Github here: [https://github.com/etourdot/xincproc/issues](https://github.com/etourdot/xincproc/issues)

## License
Copyright 2013 Emmanuel Tourdot

The XIncProc frameword is released under version 3.0 of the [LGPL Licence](http://opensource.org/licenses/lgpl-3.0.html)