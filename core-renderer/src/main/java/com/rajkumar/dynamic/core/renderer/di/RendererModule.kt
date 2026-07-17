package com.rajkumar.dynamic.core.renderer.di

import com.rajkumar.dynamic.core.renderer.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RendererModule {

    @Provides
    @Singleton
    fun provideVisibilityEvaluator(): VisibilityEvaluator = DefaultVisibilityEvaluator()

    @Provides
    @Singleton
    fun provideRendererFactory(
        renderers: Map<String, @JvmSuppressWildcards WidgetRenderer>,
        visibilityEvaluator: VisibilityEvaluator
    ): RendererFactory {
        val context = mapOf(
            "is_premium" to "true",
            "user_balance" to "124500.0",
            "app_version" to "1.0.0"
        )
        return DefaultRendererFactory(renderers, visibilityEvaluator, context)
    }

    @Provides
    @IntoMap
    @StringKey("text")
    fun provideTextRenderer(): WidgetRenderer = TextRenderer()

    @Provides
    @IntoMap
    @StringKey("column")
    fun provideColumnRenderer(): WidgetRenderer = ColumnRenderer()

    @Provides
    @IntoMap
    @StringKey("row")
    fun provideRowRenderer(): WidgetRenderer = RowRenderer()

    @Provides
    @IntoMap
    @StringKey("lazy_column")
    fun provideLazyColumnRenderer(): WidgetRenderer = LazyColumnRenderer()

    @Provides
    @IntoMap
    @StringKey("transaction_item")
    fun provideTransactionItemRenderer(): WidgetRenderer = TransactionItemRenderer()

    @Provides
    @IntoMap
    @StringKey("progress_item")
    fun provideProgressItemRenderer(): WidgetRenderer = ProgressItemRenderer()

    @Provides
    @IntoMap
    @StringKey("shimmer")
    fun provideShimmerRenderer(): WidgetRenderer = ShimmerRenderer()

    @Provides
    @IntoMap
    @StringKey("balance_card")
    fun provideBalanceCardRenderer(actionHandler: ActionHandler): WidgetRenderer = 
        BalanceCardRenderer { actionHandler.handleAction(it) }

    @Provides
    @IntoMap
    @StringKey("quick_action")
    fun provideQuickActionRenderer(actionHandler: ActionHandler): WidgetRenderer = 
        QuickActionRenderer { actionHandler.handleAction(it) }

    @Provides
    @IntoMap
    @StringKey("lottie")
    fun provideLottieRenderer(): WidgetRenderer = LottieRenderer()

    @Provides
    @IntoMap
    @StringKey("button")
    fun provideButtonRenderer(actionHandler: ActionHandler): WidgetRenderer = 
        ButtonRenderer { actionHandler.handleAction(it) }

    @Provides
    @IntoMap
    @StringKey("bill_card")
    fun provideBillCardRenderer(): WidgetRenderer = BillCardRenderer()

    @Provides
    @IntoMap
    @StringKey("payee_item")
    fun providePayeeItemRenderer(): WidgetRenderer = PayeeItemRenderer()
}
