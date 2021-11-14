package com.nanocustomer.mvp.fragment_comments_mvp;

import com.nanocustomer.models.CommentModel;
import com.nanocustomer.models.ProductModel;

public interface FragmentCommentView {
    void onSuccess(CommentModel commentModel);
    void onFailed(String msg);


}
