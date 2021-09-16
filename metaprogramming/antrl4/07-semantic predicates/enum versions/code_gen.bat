antlr4 -no-listener Enum.g4
javac Enum*.java TestEnum.java

java TestEnum
@rem enum = 0;

java TestEnum
@rem enum Temp { HOT, COLD }

java TestEnum -java5
@rem enum = 0;

java TestEnum -java5
@rem enum Temp { HOT, COLD }
