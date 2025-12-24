<script>
    import analyticsIcon from "../assets/analytics.png";
    import searchIcon from "../assets/search.png";

    import BuyModal from "../modals/BuyModal.svelte";
    import SellModal from "../modals/SellModal.svelte";
    import UpdatePriceModal from "../modals/UpdatePriceModal.svelte";
    import SearchModal from "../modals/SearchModal.svelte";
    import SearchResults from "./SearchResults.svelte";
    import AnalyticsModal from "../modals/AnalyticsModal.svelte";

    let { investments = [], loadPortfolio } = $props();

    let confirmation = $state({
        message: "",
        success: true,
        visible: false
    });

    function showConfirmation(msg, success = true) {
        confirmation.message = msg;
        confirmation.success = success;
        confirmation.visible = true;

        setTimeout(() => {
            confirmation.visible = false;
        }, 2500);
    }

    // buy modal
    let buyOpen = $state(false);
    function openBuy() {
        buyOpen = true;
    }
    function closeBuy() {
        buyOpen = false;
    }
    async function handleBuy(data) {
        try {
            const res = await fetch("/api/buy", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });
            if (!res.ok) {
                throw new Error("Buy failed");
            }
            showConfirmation(`Successfully bought ${data.quantity} shares of ${data.symbol}.`);
            closeBuy();
            console.log("Buy successful:", data);
        } catch (err) {
            showConfirmation("Purchase failed. Please try again.", false);
        }
    }

    // sell modal
    let sellOpen = $state(false);

    function openSell() {
        sellOpen = true;
    }
    function closeSell() {
        sellOpen = false;
    }
    async function handleSell(data) {
        try {
            const res = await fetch("/api/sell", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });
        if (!res.ok){
            throw new Error();
        } 
        showConfirmation(`Successfully sold ${data.quantity} shares of ${data.symbol}.`);
        console.log("Sell successful:", data);
        closeSell();
        } catch {
            showConfirmation("Sale failed. Please try again.", false);
        }
    }

    // update price modal
    let updateOpen = $state(false);

    function openUpdate() {
    updateOpen = true;
    }
    function closeUpdate() {
    updateOpen = false;
    }
    async function handleUpdate(data) {
        try {
            const res = await fetch("/api/update-price", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
            });

            if (!res.ok){
                throw new Error();
            } 
            showConfirmation(`Updated ${data.symbol} to $${data.price}.`);
            closeUpdate();
        } catch {
            showConfirmation("Update failed. Please try again.", false);
        }
    }

    // search modal
    let searchOpen = $state(false);
    let searchResults = $state([]);
    let isSearching = $state(false);

    function openSearch() {
        searchOpen = true;
    }

    function closeSearch() {
        searchOpen = false;
    }

    function clearSearch() {
        searchResults = [];
        isSearching = false;
    }

    async function handleSearch(data) {
        try {
            const res = await fetch("/api/search", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                symbol: data.symbol ?? "",
                keywords: data.keywords ?? "",
                priceRange: data.priceRange ?? ""
            })
            });

            if (!res.ok) {
            throw new Error("Search failed");
            }

            const results = await res.json();

            searchResults = results;
            isSearching = true;
            closeSearch();
        } catch (err) {
            console.error(err);
        }
    }

    const shownResults = $derived(() => (isSearching ? searchResults : investments));  

    // analytics modal
    let analyticsOpen = $state(false);
    function openAnalytics() {
        analyticsOpen = true;
    }
    function closeAnalytics() {
        analyticsOpen = false;
    }
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
        <button class="action-btn" type="button" onclick={openBuy}>
          <div class="action-icon">+</div>
          <div>Add Investment</div>
        </button>
  
        <button class="action-btn" type="button" onclick={openSell}>
          <div class="action-icon">-</div>
          <div>Sell Investment</div>
        </button>
  
        <button class="action-btn" type="button" onclick={openUpdate}>
          <div class="action-icon">↗</div>
          <div>Update Prices</div>
        </button>
  
        <button class="action-btn" type="button" onclick={openAnalytics}>
            <div class="action-icon">
              <img src={analyticsIcon} alt="Analytics Icon" class="icon-img" />
            </div>
            <div>Performance Analytics</div>
        </button>
  
        <button class="action-btn" type="button" onclick={openSearch}>
            <div class="action-icon">
              <img src={searchIcon} alt="Search Icon" class="icon-img" />
            </div>
            <div>Portfolio Search</div>
        </button>
      </div>

      {#if confirmation.visible}
        <div class="confirmation-message" class:success={confirmation.success} class:error={!confirmation.success} >
            {confirmation.message}
        </div>
      {/if}

    </div>
  </section>

    <SearchResults
        results={shownResults()}
        isFiltered={isSearching}
        onClear={clearSearch}
    />



  <BuyModal
    open={buyOpen}
    onClose={closeBuy}
    onSubmit={handleBuy}
  />

  <SellModal
    open={sellOpen}
    onClose={closeSell}
    onSubmit={handleSell}
  />

  <UpdatePriceModal
    open={updateOpen}
    onClose={closeUpdate}
    onSubmit={handleUpdate}
  />

  <SearchModal
    open={searchOpen}
    onClose={closeSearch}
    onSubmit={handleSearch}
  />

  <AnalyticsModal
    open={analyticsOpen}
    investments={investments}
    onClose={closeAnalytics}
  />




  