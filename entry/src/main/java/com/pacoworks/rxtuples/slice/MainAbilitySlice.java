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
import com.pacoworks.rxtuples.ResourceTable;

/** MainAbilitySlice. **/
public class MainAbilitySlice extends AbilitySlice {
    Button pairBtn;
    Button tripletBtn;
    Intent it;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        pairBtn = (Button) findComponentById(ResourceTable.Id_make_pair_btn);
        tripletBtn = (Button) findComponentById(ResourceTable.Id_make_triplet_btn);

        pairBtn.setClickedListener(component -> {
            // Add the event processing logic for a click event on the button.
            it = new Intent();
            it.setParam("function", 1);
            present(new FunctionAbilitySlice(), it);
        });

        tripletBtn.setClickedListener(component -> {
            // Add the event processing logic for a click event on the button.
            it = new Intent();
            it.setParam("function", 2);
            present(new FunctionAbilitySlice(), it);
        });

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
