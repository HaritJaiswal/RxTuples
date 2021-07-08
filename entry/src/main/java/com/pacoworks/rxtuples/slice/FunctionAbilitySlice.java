/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacoworks.rxtuples.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;
import com.pacoworks.rxtuples.ResourceTable;
import com.pacoworks.rxtuples.RxTuples;
import rx.Observable;
import org.javatuples.Pair;
import org.javatuples.Triplet;


/** FunctionAbilitySlice. **/
public class FunctionAbilitySlice extends AbilitySlice {
    TextField entry1;
    TextField entry2;
    TextField entry3;
    Button createBtn;
    Text display;
    Text enterData3;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_slice_function);

        entry1 = (TextField) findComponentById(ResourceTable.Id_entry_1);
        entry2 = (TextField) findComponentById(ResourceTable.Id_entry_2);
        entry3 = (TextField) findComponentById(ResourceTable.Id_entry_3);
        createBtn = (Button) findComponentById(ResourceTable.Id_create_btn);
        display = (Text) findComponentById(ResourceTable.Id_function_display_text);
        enterData3 = (Text) findComponentById(ResourceTable.Id_enter_data3);


        if (intent != null) {
            int func = intent.getIntParam("function", 1);
            if (func == 1) {
                enterData3.setVisibility(Component.HIDE);
                entry3.setVisibility(Component.HIDE);
                createBtn.setClickedListener(component -> createPair());
            } else {
                enterData3.setVisibility(Component.VISIBLE);
                entry3.setVisibility(Component.VISIBLE);
                createBtn.setClickedListener(component -> createTriplet());
            }
        }
    }

    private void createTriplet() {
        Observable<Triplet<String, String, String>> result = Observable.zip(Observable.just(entry1.getText()),
                Observable.just(entry2.getText()),
                Observable.just(entry3.getText()), RxTuples.<String, String, String>toTriplet());

        result.subscribe(tmp -> {
            Triplet<String, String, String> trip = tmp;
            new ToastDialog(getContext())
                    .setText("Success!").show();
            display.setText(trip.toString());
        });
    }

    private void createPair() {
        Observable<Pair<String, String>> result = Observable.zip(Observable.just(entry1.getText()),
                Observable.just(entry2.getText()),
                RxTuples.<String, String>toPair());

        result.subscribe(tmp -> {
            Pair<String, String> p = tmp;
            new ToastDialog(getContext())
                    .setText("Success!").show();
            display.setText(p.toString());
        });
    }

    @Override
    protected void onBackground() {
        super.onBackground();
        entry1.setText("");
        entry2.setText("");
        entry3.setText("");
        display.setText("");
    }
}
