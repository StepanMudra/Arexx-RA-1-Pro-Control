#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

Adafruit_PWMServoDriver pwm = Adafruit_PWMServoDriver();

int MIN_PULSE_WIDTH;
int MAX_PULSE_WIDTH;
#define DEFAULT_PULSE_WIDTH   1500
#define FREQUENCY             50

int pin;
int setUp;
bool first = true;

#define BUFFER_SZ 10

void setup() {
  Serial.begin(9600);
  pwm.begin();
  pwm.setPWMFreq(FREQUENCY);
}


// incoming command temp vars
String command;
int firstParam;
int secondParam;

int tokenCounter = 0;

// token buffer
static char buffer[BUFFER_SZ];
static size_t lg = 0;


// perform a command 
void doCommand(String cmd, int fp, int sp){
  if (cmd.equals("m")){
      Serial.print("Servo ");
      Serial.print(fp);
      Serial.print(" uhel ");
      Serial.println(sp);
      setPin(firstParam);
      pwm.setPWM(pin, 0, pulseWidth(secondParam));
  } else {
      // no command or unrecognized
  }
}

void loop() {
    // parse incomming commands
    while (Serial.available() > 0) {
         char c = Serial.read();
         Serial.print(c);
         if (lg < BUFFER_SZ - 1) {
          // on whitespace, process token buffer
          if (c == ' ' || c == '\n'){
              switch (tokenCounter){
                case 0:
                  command = String(buffer);
                  break;
                case 1:
                  firstParam = atoi(buffer);   
                  break;
                case 2:
                  secondParam = atoi(buffer);
                  break;
              }
              // when we have all the parameters, perform a command
              if (tokenCounter >=2){
                  doCommand(command, firstParam, secondParam);
                  // reset temp variables for next command
                  command = "";
                  firstParam = 0;
                  secondParam = 0;
                  tokenCounter = 0;
              } else {
                tokenCounter++;
              }

              // reset buffer for next token
              lg = 0;
              memset(&buffer[0], 0, sizeof(buffer));
              
            } else if (c == '\r'){
              // skip these characters
            } else {
              // add char to the token bufffer
              buffer[lg++] = c;
            }
        }
    }
}



void setPin(int index){
    switch(index){
    case 0:
      pin = 1;
      break;
    case 1:
      pin = 3;
      break;
    case 2:
      pin = 4;
      break;
    case 3:
      pin = 6;
      break;
    case 4:
      pin = 8;
      break;
    case 5:
      pin = 10;
      break;
  }
}


int pulseWidth(int angle)
{
  int pulse_wide, analog_value;
  if(pin == 8 || pin == 10){
    pulse_wide   = map(angle, 0, 180, 500, 2350);
  }else{
    pulse_wide   = map(angle, 0, 180, 350, 2700);
  }
  analog_value = int(float(pulse_wide) / 1000000 * FREQUENCY * 4096);
  return analog_value;
}
