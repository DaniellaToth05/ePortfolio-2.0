<script>
    import PortfolioCard from "./PortfolioCard.svelte";
    let {
        investments = []
    } = $props();
</script>

<section class="hero">
    <div class="container">
      <div class="hero-content">
        <!-- LEFT -->
        <div class="hero-text">
          <div class="greeting">Good Afternoon, Daniella</div>
          <h1>Your Investments, Simplified.</h1>
          <p class="subtitle">
            Stay on top of your portfolio — down to the last dollar.
          </p>
        </div>
  
        <!-- RIGHT -->
        <PortfolioCard investments={investments} />
      </div>
    </div>

    <div class="floating-element leaf-1"></div>
    <div class="floating-element leaf-2"></div>
    <div class="floating-element sage-accent"></div>
  </section>
  