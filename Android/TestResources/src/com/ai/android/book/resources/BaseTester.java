package com.ai.android.book.resources;

import android.content.Context;

public class BaseTester
{
   public BaseTester(Context ctx, IReportBack target)
   {
      mReportTo = target;
      mContext = ctx;
   }

   protected IReportBack mReportTo;
   protected Context     mContext;
}
