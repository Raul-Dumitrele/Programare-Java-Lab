#include <stdio.h>
#include <stdlib.h>
#include <time.h>
typedef enum{false,true}boolean;
void Deplasare(int s,int d){
    int i,j,x;
    boolean bul;
    i=s;
    j=2*i;
    x=a[i];
    bul=false;
    while(j<=d && bul==false){
        if(j<d)
    }

}
void generare(int a[], int n) {
    int i;
    srand(time(NULL));
    for (i = 0; i < n; i++)
        a[i] = rand() % 100;
}

void afisare(int a[], int n) {
    int i;
    for (i = 0; i < n; i++)
        printf("%d ", a[i]);
    printf("\n");
}

int biti(int x, int n, int k) {
    return (x >> k) & ~(~0 << n);
}

void radixInterschimbare(int a[], int s, int d, int b) {
    int i, j, aux;
    if (s < d && b >= 0) {
        i = s;
        j = d;
        do {
            while (i < j && biti(a[i], 1, b) == 0) i++;
            while (i < j && biti(a[j], 1, b) == 1) j--;
            aux = a[i];
            a[i] = a[j];
            a[j] = aux;
        } while (i != j);
        if (biti(a[d], 1, b) == 0) j = j + 1;
        radixInterschimbare(a, s, j - 1, b - 1);
        radixInterschimbare(a, j, d, b - 1);
    }
}

void radixDirect(int a[], int n, int m) {
    int k, i;
    int t[20];
    int Nr[16];
    for (k = 0; k < sizeof(int) * 8 / m; k++) {
        for (i = 0; i < 16; i++)
            Nr[i] = 0;
        for (i = 0; i < n; i++)
            Nr[biti(a[i], m, m * k)]++;
        for (i = 1; i < 16; i++)
            Nr[i] = Nr[i - 1] + Nr[i];
        for (i = n - 1; i >= 0; i--) {
            t[Nr[biti(a[i], m, m * k)] - 1] = a[i];
            Nr[biti(a[i], m, m * k)]--;
        }
        for (i = 0; i < n; i++)
            a[i] = t[i];
    }
}

void sortQ(int a[], int s, int d) {
    int i, j, m, aux;
    i = s;
    j = d;
    m = a[(s + d) / 2];
    do {
        while (a[i] < m) i++;
        while (a[j] > m) j--;
        if (i <= j) {
            aux = a[i];
            a[i] = a[j];
            a[j] = aux;
            i++;
            j--;
        }
    } while (i <= j);
    if (i < d) sortQ(a, i, d);
    if (j > s) sortQ(a, s, j);
}
void shellSort(int a[],int n){
    int h[3]={5,3,1};
    int i,j,l,k,aux;
    for(l=0;l<=2;l++){
        k=h[l];
        for(i=k;i<n;i++){
            aux=a[i];
            j=i-k;
            while(j>=0&&aux>a[j]){
                a[j + k]=a[j];
                j=j-k;
            }
            a[j+k]=aux;
        }
    }
}
int main() {
    int a[20], n = 10, b;
    generare(a, n);
    afisare(a, n);
    b = sizeof(int) * 8 - 1;
    radixInterschimbare(a, 0, n - 1, b);
    afisare(a, n);
    int m = 4;
    radixDirect(a, n, m);
    afisare(a, n);
    sortQ(a,0,n-1);
    afisare(a, n);
    shellSort(a,n);
    afisare(a, n);
    return 0;
}
