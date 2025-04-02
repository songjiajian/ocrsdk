package com.sg.ocr;

import androidx.annotation.NonNull;

import java.util.List;

public interface OcrTextListener {
    void onSuccess(List<String> list);
    void OnFailure(@NonNull Exception var1);
}
