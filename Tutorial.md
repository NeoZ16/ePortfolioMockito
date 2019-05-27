### Tutorial [Mockito](https://site.mockito.org/)
#### Einbinden in das Projekt
[Mockito](https://site.mockito.org/) ist ein Java Framework um schnell und ohne überflüssigen Code Mock/Spy Objekte zu erzeugen.
Im folgenden handelt es sich um eine Einführung wie Mockito in ein Maven Projekt eingebunden werden kann und erste Schritte zur Verwendung von Mockito.
Einen kleinen Überblick über den Inhalt findet sich in der [DemockcracyTest](https://github.com/NeoZ16/ePortfolioMockito/blob/master/mockito_example/src/test/java/DemockcracyTest.java) Klasse.

Diese Dependency muss in die `pom.xml` hinzugefügt werden.
```
<!-- Mockito dependency -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.27.0</version>
            <scope>test</scope>
        </dependency>
```
- [pom.xml des Beispielprojekts](https://github.com/NeoZ16/ePortfolioMockito/blob/master/mockito_example/pom.xml)
- [MvnRepositoryLink von Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core)

#### Ein erster Mock
Zunächst kann mit der statischen Methode `mock(Class class)` aus dem Mockito Paket ein Mock-Object erstellt werden. Das Verhalten dieses Objekts kann mit `when()` festgelegt, und mit `verify()` überprüft werden.
```
@Test
public void testPlay(){
  President mockPresident = mock(President.class);
  when(mockPresident.playGolf()).thenReturn(10);
  assertEquals(10, mockPresident.playGolf());
  verify(mockPresident, atLeastOnce()).playGolf();
}
```

#### Weitere Möglichkeiten

Es ist auch möglich gemockte Objekte an andere Objekte zu übergeben. Auch hier kann überprüft werden, welche Methoden, mit welchen Werten und wie oft aufgerufen wurden.
```
@Test
public void testStateVisit(){
    President mockPresident = mock(President.class);
    Demockcracy demockcracy = new Demockcracy(mockPresident);
    demockcracy.stateVisit();

    verify(mockPresident, atLeastOnce()).greet();
}
```

Im letzten Beispiel werden ArgumentMatcher und die Möglichkeit Exception zu werfen benutzt.
ArgumentMatcher lassen es zu ein bestimmten Typ von Argumenten abzudecken. Zum Beispiel über `anyInt()`. Im Beispiel wird der Matcher `argThat()` benutzt. Mit diesem kann über eine Methode definiert werden wie das Argument für den speziellen Falls aussehen soll.
Im Beispiel wird das in einem Lambda Ausdruck gemacht in welchem definiert wird, dass die Exception nur geworfen wird wenn der eingebene String mehr als 5 Zeichen hat.
Die Matcher können auch für das verifizieren benutzt werden, wie im Beispiel `anyString()`

```
@Test
public void testPassbill()throws Exception{
    doThrow(new Exception("Too long didnt read")).when(mockPresident)
      .signBill(argThat( str -> str.length() > 5));

    assertEquals(demockcracy.passBill("1234"), true);
    assertEquals(demockcracy.passBill("1234567"),false);

    verify(mockPresident, times(2)).signBill(anyString());
}
```

`argThat` lässt sich auch mit einem Interface, beziehungsweise mit einer anyonmen Klasse spezifizieren.
```
//argThat mit anonymer KLasse
doThrow(new Exception("Too long didnt read")).when(mockPresident)
      .signBill(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String s) {
                return str.length() > 5;
            }
        }));
```

#### Ausblick

[Mockito](https://site.mockito.org/) bietet noch viele weiter Möglichkeiten wie zum Beispiel das einfangen und abfragen der Werte mit welchen die Methoden aufgerufen wurde oder das partielle Mocken von Klassen.
Diese findet man alle in der [Dokumenation](https://static.javadoc.io/org.mockito/mockito-core/2.27.0/org/mockito/Mockito.html).
