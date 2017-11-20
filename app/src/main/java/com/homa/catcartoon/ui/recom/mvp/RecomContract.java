/*
 * Copyright 2016, The Android Open Source Project
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

package com.homa.catcartoon.ui.recom.mvp;


import com.homa.catcartoon.Mvp.BasePresenter;
import com.homa.catcartoon.Mvp.BaseView;
import com.homa.catcartoon.ui.recom.bean.Banner;
import com.homa.catcartoon.ui.recom.bean.MySection;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface RecomContract {

    interface View extends BaseView<Presenter> {

        void getData(List<Banner> banners,List<String> url,List<MySection> data);
        void getDataError(ApiException e, String method);

    }

    interface Presenter extends BasePresenter {
    }
}
