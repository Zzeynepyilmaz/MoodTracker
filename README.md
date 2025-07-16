# 🧠 MoodTracker

Minimalist ve duygusal farkındalık odaklı bir Android uygulaması.  
Her gün ruh halinizi seçin, küçük bir not bırakın ve geçmişinizi takvimden takip edin.

---

## 📱 Özellikler

- 📅 **Aylık takvim görünümü**
- 📍 Güne tıklayarak geçmiş mood'u ve notu görüntüleme
- ✍️ Bugün için mood + not ekleme (yalnızca bir kez)
- 🎨 Mood’lar renkle ve emojiyle temsil edilir
- 💾 Veriler **Room** ile kalıcı olarak saklanır
- ✅ Aynı güne ikinci kez kayıt yapılamaz (otomatik engellenir)

---

## 🛠️ Kullanılan Teknolojiler

- 🧪 Kotlin + Jetpack Compose
- 🧩 Room Persistence Library
- 🔄 StateFlow tabanlı MVI mimarisi
- 🎨 Material 3 ile modern UI

---

## 🚀 Uygulama Akışı

1. **Takvim ekranı** açılır.
2. Kullanıcı herhangi bir güne tıklar:
    - O güne ait kayıt varsa, mood ve not görünür.
    - Bugün’e tıklanmışsa ve kayıt yoksa, **mood girme alanı açılır.**
3. Mood kaydedildiğinde takvimde renkli bir baloncuk olarak görünür.

---

## 🌈 Mood Renk Kodları

| Mood      | Emoji | Renk      |
|-----------|--------|-----------|
| Happy     | 😊     | Sarı 💛     |
| Sad       | 😢     | Mavi 💙     |
| Angry     | 😡     | Kırmızı ❤️  |
| Tired     | 😴     | Gri ⚪       |
| Calm      | 😌     | Yeşil 💚     |
| Stressed  | 😣     | Turuncu 🧡   |

---

## 📌 Geliştirme Notları

- Mood kayıtları yalnızca **bugün** için yapılabilir.
- Aynı gün içinde birden fazla kayıt yapılamaz.
- Kod yapısı modülerdir, yeni özellikler kolayca eklenebilir:
    - Mood silme
    - Karanlık tema desteği
    - Haftalık görünüm
    - Grafiksel ruh hali eğrisi

---

## 🖼️ Ekran Görüntüleri (isteğe bağlı)

📸 Henüz eklenmedi. Uygulama stabil hale geldiğinde ekran görüntüleri buraya gelecek.

---

## 💬 Geri Bildirim & Katkı

Bu uygulama bireysel bir öğrenme/gelişim projesidir.  
Fikir, öneri veya katkılarını paylaşmak istersen bana ulaşabilirsin 💌

---
