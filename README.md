# Krypto
## An android application that uses Optical Character Recognition and Image Processing to ease Reading.

The application consists of three major uses:
1. **Reading** - The image of the article taken is converted to text format via **Tesseract OCR** library.
   Image enhancement techniques were used to improve the quality of image and henceforth, the accuracy of OCR.
   This includes usage of **ScanLibrary** for Android-Native.
   The application then converts each text to a **Spannable String** which is clickable and provides with another three options
   to work on: </br>
      (i) Meaning -through wordsApi </br>
      (ii) Pronounciation- using tts of Android </br>
      (iii) Add to Dictionary - stores the majorly used words to the local dB.</br>
2. **Text to speech** to find the pronounciation of a word.
3. **Speech to text** via Google mike integration

![alt text](https://raw.githubusercontent.com/sakshichahal53/Krypto/master/krypto scs/Copy of Screenshot_2017-04-24-12-35-05.png)
