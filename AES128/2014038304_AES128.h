#define ENC 1 

#define DEC 0 

typedef unsigned char BYTE;

void AES128(BYTE *plain, BYTE *cipher, BYTE *key, int mode);