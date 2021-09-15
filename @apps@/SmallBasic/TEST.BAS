PRINT "This program converts gallons to liters." 
 
100 GOSUB 200 
  INPUT "Again? (1 or 0): ", x 
  IF x = 1 THEN GOTO 100 
  END 
 
200 INPUT "Enter gallons: ", g 
  l = g * 3.7854 
  PRINT g; "gallons is"; l; "liters." 
  RETURN

