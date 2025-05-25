import java.util.*;  // Importowanie klasy Scanner do wczytywania danych

public class QuickSort {

    // Algorytm QuickSort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {  // Sprawdzenie, czy przedział jest poprawny
            int pivotIndex = partition(array, low, high);  // Wyznaczenie indeksu elementu piwotowego
            quickSort(array, low, pivotIndex - 1);  // Rekurencyjne sortowanie lewej części
            quickSort(array, pivotIndex + 1, high);  // Rekurencyjne sortowanie prawej części
        }
    }

    // Podział tablicy na części względem piwota
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];  // Wybór piwota - ostatni element
        int i = (low - 1);  // Indeks mniejszego elementu
        for (int j = low; j < high; j++) {  // Iteracja po elementach
            if (array[j] <= pivot) {  // Jeśli element jest mniejszy lub równy piwotowi
                i++;  // Zwiększenie indeksu mniejszego elementu
                int temp = array[i];  // Zamiana elementów
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];  // Przesunięcie piwota na właściwe miejsce
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;  // Zwrócenie indeksu piwota
    }

    // Pomiar użycia pamięci
    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();  // Uzyskanie referencji do środowiska uruchomieniowego
        runtime.gc();  // Uruchomienie garbage collectora
        return runtime.totalMemory() - runtime.freeMemory();  // Obliczenie zajętej pamięci
    }

    public static void main(String[] args) {
        System.out.println("Copyright ©");
        System.out.println("Krystian Zatka, 124048");
        // Wyświetlenie informacji o autorze i prawach autorskich
        System.out.println("Copyright (c) 2025 Jan Kowalski. Wszelkie prawa zastrzeżone.");
        System.out.println("Autor: Jan Kowalski");

        int[] nValues = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};  // Wartości n

        for (int n : nValues) {  // Iteracja po różnych wartościach n
            System.out.println("\nWyniki dla n = " + n + ":");
            long totalDuration = 0;  // Całkowity czas sortowania dla zbioru
            long totalMemory = 0;  // Całkowite zużycie pamięci dla zbioru

            for (int size = 1; size <= n; size++) {  // Dla każdej liczby od 1 do n
                int[] array = new int[size];  // Tworzenie tablicy o bieżącym rozmiarze
                for (int i = 0; i < size; i++) {
                    array[i] = (int) (Math.random() * 100);  // Generowanie losowych liczb z zakresu 0-99
                }

                long startTime = System.nanoTime();  // Pomiar czasu rozpoczęcia
                long startMemory = getUsedMemory();  // Pomiar zajętej pamięci przed sortowaniem

                quickSort(array, 0, array.length - 1);  // Sortowanie tablicy

                long endTime = System.nanoTime();  // Pomiar czasu zakończenia
                long endMemory = getUsedMemory();  // Pomiar zajętej pamięci po sortowaniu

                long duration = (endTime - startTime) / 1_000_000;  // Czas w milisekundach
                long memoryUsed = (endMemory - startMemory) / 1024;  // Zużyta pamięć w kilobajtach

                memoryUsed = Math.max(memoryUsed, 1);  // Zapewnienie dodatniej wartości pamięci

                totalDuration += duration;  // Sumowanie czasu
                totalMemory += memoryUsed;  // Sumowanie pamięci

                // Wyświetlenie wyników sortowania
                System.out.println("Posortowano " + size + " elementów w " + duration + " ms, pamięć użyta: " + memoryUsed + " KB");
            }

            // Obliczenie i wyświetlenie średnich
            double avgDuration = (double) totalDuration / n;
            double avgMemory = (double) totalMemory / n;
            System.out.println("\nŚredni czas dla n = " + n + ": " + avgDuration + " ms");
            System.out.println("Średnia pamięć dla n = " + n + ": " + avgMemory + " KB\n");
        }
    }
}
