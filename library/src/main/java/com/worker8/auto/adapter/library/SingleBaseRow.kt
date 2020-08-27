package com.worker8.auto.adapter.library

import com.worker8.auto.adapter.library.BaseRow
import com.worker8.auto.adapter.library.NoAutoData

abstract class SingleBaseRow(layoutResId: Int) : BaseRow<NoAutoData>(NoAutoData(0), layoutResId)