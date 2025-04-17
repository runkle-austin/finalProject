# JavaFX Project Setup

This project uses JavaFX (v24) and is set up with the Model-View-Controller + Observer pattern. The JavaFX SDK is already included in the project (`javafx-sdk-24/` at the root), so no download is needed.

## 🛠 IntelliJ Setup (One-Time)

### 1. Add JavaFX Libraries
1. Open IntelliJ and go to:
   File > Project Structure > Libraries > + > Java
2. Select: `/javafx-sdk-24/lib/`
3. Click OK → Apply → OK.

### 2. Create a Run Configuration
1. Go to: Run > Edit Configurations > + > Application
2. Set:
- **Name**: `JavaFX` (or something similar)
- **Main class**: `view.GUIView` (or your entry class)
- Click **"Modify Options"** → check **"Add VM options"**
- In **VM options**, paste:

  ```
  --module-path ./javafx-sdk-24/lib --add-modules javafx.controls,javafx.web --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
  ```

3. Apply → OK.

### 3. Run the App
Run the application like normal at the top right, and you should see a JavaFX popup

---

Let me know if you have any issues with this.
