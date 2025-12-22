<script>
    import analyticsIcon from "../assets/analytics.png";
    import searchIcon from "../assets/search.png";
</script>

<section class="container">
    <div class="action-section">
      <div class="action-header">
        <h2 class="action-title">Portfolio Dashboard</h2>
        <p class="action-subtitle">
          Track performance, update values, and manage assets with ease.
        </p>
      </div>
  
      <div class="action-grid">
        <button class="action-btn" type="button">
          <div class="action-icon">+</div>
          <div>Add Investment</div>
        </button>
  
        <button class="action-btn" type="button">
          <div class="action-icon">-</div>
          <div>Sell Investment</div>
        </button>
  
        <button class="action-btn" type="button">
          <div class="action-icon">↗</div>
          <div>Update Prices</div>
        </button>
  
        <button class="action-btn" type="button">
            <div class="action-icon">
              <img src={analyticsIcon} alt="Analytics Icon" class="icon-img" />
            </div>
            <div>Performance Analytics</div>
        </button>
  
        <button class="action-btn" type="button">
            <div class="action-icon">
              <img src={searchIcon} alt="Search Icon" class="icon-img" />
            </div>
            <div>Portfolio Search</div>
        </button>
      </div>
    </div>
  </section>
  